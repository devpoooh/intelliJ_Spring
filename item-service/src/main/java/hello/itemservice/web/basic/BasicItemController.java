package hello.itemservice.web.basic;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

//@RequiredArgsConstructor 롬복에서 final이 붙은 변수에 대한 생성자를 만들어 준다
@Slf4j
@Controller
@RequestMapping("/basic/items")
public class BasicItemController {

    private final ItemRepository itemRepository;

    @Autowired //롬복으로 만들어진 생성자
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);

        return "basic/items";
    }


    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);

        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    //form의 name으로 데이터가 넘어온다
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){
        Item item = new Item(itemName,price,quantity);

        itemRepository.save(item);

        model.addAttribute("item",item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model){ //알아서 item이라는 모델에 파라미터 값 넣어준다. model도 생략 가능

        itemRepository.save(item);

        model.addAttribute("item",item); //생략 가능

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model){ //알아서 item이라는 모델에 파라미터 값 넣어준다. model도 생략 가능

        //@ModelAttribute 속성 생략 가능
        //Item(클래스명) -> item 소문자로 만들어서 attributeName으로 넘겨준다
       itemRepository.save(item);

        model.addAttribute("item",item); //생략 가능

        return "basic/item";
    }

    //@ModelAttribute도 생략 가능
//    @PostMapping("/add")
    public String addItemV4(Item item){ //알아서 item이라는 모델에 파라미터 값 넣어준다. model도 생략 가능

        itemRepository.save(item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV5(Item item){ //알아서 item이라는 모델에 파라미터 값 넣어준다. model도 생략 가능

        itemRepository.save(item);

        return "redirect:/basic/items/"+item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){ //알아서 item이라는 모델에 파라미터 값 넣어준다. model도 생략 가능
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId",savedItem.getId());
        redirectAttributes.addAttribute("status",true);

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
    }
}
